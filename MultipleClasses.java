/*
CS461 - Homework 3B
Group of 3:
	- Xianqiu Yu
	- Guodong Lu
	- Hao Lam

Work's Explanation:
	- The format of each state will be: 
		(cannibalLeft,missionaryLeft,boat,cannibalRight,missionaryRight)
			where:
				+ cannibalLeft: number of cannibals on  the LEFT side of river at current
				+ missionaryLeft: number of missionaries on  the LEFT side of river at current
				+ boat: location of the boat at current; 'L' indicates at left side - 'R' indicates at right side
				+ cannibalRight: number of cannibals on  the RIGHT side of river at current
				+ missionaryRight: number of missionaries on  the RIGHT side of river at current
	
	- For this problem, we choose to apply Breadth-first search (BFS) algorithm to run for the solution.

*/

package angelcs461H3;
import java.util.ArrayList;
import java.util.*;

enum Position {RIGHT, LEFT}

class MultipleClasses {
		public static void main(String[] args) {
			System.out.println("\n****** Missionaries and Cannibals Implementation ******");
			State initialState = new State (3, 3, Position.LEFT, 0, 0);
			runBFS(initialState);
		}

		private static void runBFS(State initialState) {
			BFS search = new BFS();
			State solution = search.action(initialState);
			result(solution);
		}

		private static void result(State solution) {
			if (null == solution) {
				//if no solution found
				System.out.print("\nNo solution found.");
			} else {
				System.out.println("\nSolution (cannibalLeft,missionaryLeft,boat,cannibalRight,missionaryRight): ");
				List<State> path = new ArrayList<State>();
				State state = solution;
				while(null!=state) {
					path.add(state);
					state = state.getParentState();
				}

				int depth = path.size() - 1;
				// print out each state of the solution
				for (int i = depth; i >= 0; i--) {
					state = path.get(i);
					if (state.isGoal()) {
						// if reach goal state, print out the state only
						System.out.print(state.toString());
					} else {
						// if goal not reach, continue print out state and '->'
						System.out.print(state.toString() + " -> ");
					}
				}
				//print out the Depth (number of steps) to get to the goal state.
				System.out.println("\nDepth (nunber of steps): " + depth);
				
			}
		}
		
		
	
}

// use the breadth-first search algorithm 
class BFS {
	
	// pass initial state as input to the bread first search algorithm that returns the result to the problem.
	public State action(State initialState) {
		if (initialState.isGoal()) {
			//if 'initialState' = goal state -> return immediately
			return initialState;
		}
		Queue<State> frontier = new LinkedList<State>();	// FIFO queue
		Set<State> explored = new HashSet<State>();
		frontier.add(initialState);
		while (true) {
			if (frontier.isEmpty()) {
				return null;	// Fail
			}
			State state = frontier.poll(); // remove and return the state up front
			explored.add(state); 
			List<State> successors = state.successors(); //get the list of successors for current state
			
			// run through successors list
			for (State child : successors) {
				if (!explored.contains(child) || !frontier.contains(child)) {
					if (child.isGoal()) {
						return child;
					}
					frontier.add(child);
				}
			}
		}
	}
}

// State class
class State {

	private int cannibalLeft;
	private int missionaryLeft;
	private int cannibalRight;
	private int missionaryRight;
	private Position boat;

	private State parentState;

	public State(int cannibalLeft, int missionaryLeft, Position boat,
			int cannibalRight, int missionaryRight) {
		this.cannibalLeft = cannibalLeft;
		this.missionaryLeft = missionaryLeft;
		this.boat = boat;
		this.cannibalRight = cannibalRight;
		this.missionaryRight = missionaryRight;
	}

	//isGoal method check if the goal is meet. Return TRUE/FALSE value	
	public boolean isGoal() {
		return cannibalLeft == 0 && missionaryLeft == 0;
	}

	//isValid method check if the state carries valid values. Return TRUE/FALSE
	public boolean isValid() {
		if (missionaryLeft >= 0 && missionaryRight >= 0 && cannibalLeft >= 0 && cannibalRight >= 0
	               && (missionaryLeft == 0 || missionaryLeft >= cannibalLeft)
	               && (missionaryRight == 0 || missionaryRight >= cannibalRight)) {
			return true;
		}
		return false;
	}
	
	//successors method checks the actions that can be applied to a particular state and returns the valid successor states.
	public List<State> successors() {
		List<State> successors = new ArrayList<State>();
		
		if (boat == Position.LEFT) {
			// boat is on LEFT side. Check & add all possible next states can get for current state
			testAndAdd(successors, new State(cannibalLeft, missionaryLeft - 2, Position.RIGHT,
					cannibalRight, missionaryRight + 2)); 											// Two missionaries cross left to right.
			testAndAdd(successors, new State(cannibalLeft - 2, missionaryLeft, Position.RIGHT,
					cannibalRight + 2, missionaryRight)); 											// Two cannibals cross left to right.
			testAndAdd(successors, new State(cannibalLeft - 1, missionaryLeft - 1, Position.RIGHT,
					cannibalRight + 1, missionaryRight + 1)); 										// One missionary and one cannibal cross left to right.
			testAndAdd(successors, new State(cannibalLeft, missionaryLeft - 1, Position.RIGHT,
					cannibalRight, missionaryRight + 1)); 											// One missionary crosses left to right.
			testAndAdd(successors, new State(cannibalLeft - 1, missionaryLeft, Position.RIGHT,
					cannibalRight + 1, missionaryRight)); 											// One cannibal crosses left to right.
		} else {
			// boat is on RIGHT side. Check & add all possible next states can get for current state
			testAndAdd(successors, new State(cannibalLeft, missionaryLeft + 2, Position.LEFT,
					cannibalRight, missionaryRight - 2)); 											// Two missionaries cross right to left.
			testAndAdd(successors, new State(cannibalLeft + 2, missionaryLeft, Position.LEFT,
					cannibalRight - 2, missionaryRight)); 											// Two cannibals cross right to left.
			testAndAdd(successors, new State(cannibalLeft + 1, missionaryLeft + 1, Position.LEFT,
					cannibalRight - 1, missionaryRight - 1)); 										// One missionary and one cannibal cross right to left.
			testAndAdd(successors, new State(cannibalLeft, missionaryLeft + 1, Position.LEFT,
					cannibalRight, missionaryRight - 1)); 											// One missionary crosses right to left.
			testAndAdd(successors, new State(cannibalLeft + 1, missionaryLeft, Position.LEFT,
					cannibalRight - 1, missionaryRight)); 											// One cannibal crosses right to left.
		}
		return successors;
	}

	// testAndAdd method checks if the newState is valid. 
		// And, if it does, add newState to the successors list and set its parrent to current state (this)
	private void testAndAdd(List<State> successors, State newState) {
		if (newState.isValid()) {
			newState.setParentState(this);
			successors.add(newState); // add to 'successors' list
		}
	}

	public int getCannibalLeft() {
		return cannibalLeft;
	}

	public void setCannibalLeft(int cannibalLeft) {
		this.cannibalLeft = cannibalLeft;
	}

	public int getMissionaryLeft() {
		return missionaryLeft;
	}

	public void setMissionaryLeft(int missionaryLeft) {
		this.missionaryLeft = missionaryLeft;
	}

	public int getCannibalRight() {
		return cannibalRight;
	}

	public void setCannibalRight(int cannibalRight) {
		this.cannibalRight = cannibalRight;
	}

	public int getMissionaryRight() {
		return missionaryRight;
	}

	public void setMissionaryRight(int missionaryRight) {
		this.missionaryRight = missionaryRight;
	}

	public void toLeft() {
		boat = Position.LEFT;
	}

	public void toRight() {
		boat = Position.RIGHT;
	}

	public boolean onLeft() {
		return boat == Position.LEFT;
	}

	public boolean onRight() {
		return boat == Position.RIGHT;
	}

	public State getParentState() {
		return parentState;
	}

	public void setParentState(State parentState) {
		this.parentState = parentState;
	}

	public String toString() {
		if (boat == Position.LEFT) {
			return "(" + cannibalLeft + "," + missionaryLeft + ",L,"
        			+ cannibalRight + "," + missionaryRight + ")";
		} else {
			return "(" + cannibalLeft + "," + missionaryLeft + ",R,"
        			+ cannibalRight + "," + missionaryRight + ")";
		}
     }

	public boolean equals(Object obj) {
		if (!(obj instanceof State)) {
			return false;
		}
		State s = (State) obj;
        return (s.cannibalLeft == cannibalLeft && s.missionaryLeft == missionaryLeft
        		&& s.boat == boat && s.cannibalRight == cannibalRight
        		&& s.missionaryRight == missionaryRight);
	}
}

