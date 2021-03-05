//
//  main.cpp
//  cannibals_and_missionaries
//
//  Created by Hao Lam on 3/4/21.
//

#include <iostream>
#include "State.hpp"
#include "Carry.hpp"
#include "Side.hpp"
#include <vector>
using namespace std;

//function checking if carry_action valid
bool isLegalAction (State currentState, Carry action)
{
    int action_c = action.getCannibals();
    int action_m = action.getMissionaries();
    if (action_c > action_m || (action_m ==0 && action_c ==0))
    {
        return false; //cannibals > missionaries
                    // cannibals = missionaries = 0
    }
    //check boat side
    if (currentState.leftSide.getBoatSide()==1)
    {
        //boat on the LEFT
        //check action valid or not
        int num_c = currentState.leftSide.getCannibals();
        int num_m = currentState.leftSide.getMissionaries();
        if (action_c >num_c || action_m > num_m)
        {
            // invalid # of cannibals or missionaries. Exceed current # at current state
            return false;
        }
        else
        {
            return true;
        }
      
    }
    else
    {
        //boat on the RIGHT
        //check action valid or not
        int num_c = currentState.leftSide.getCannibals();
        int num_m = currentState.leftSide.getMissionaries();
        if (action_c >num_c || action_m > num_m)
        {
            // invalid # of cannibals or missionaries. Exceed current # at current state
            return false;
        }
        else
        {
            return true;
        }
    }
}
// get a list of all possible action at current state
void getNextMoves(State currentState, vector <Carry> &possible_actions)
{
    for (int x=0 ; x<4; x++)
    {
        for (int y=0; y<4; y++)
        {
            Carry carry = new Carry(x,y);
            bool check = isLegalAction(currentState,carry);
            if (check == true)
            {
                //legal action -> add to the action_list
                possible_actions.push_back(carry);
            }
        }
    }
}
// get next state
State get_nextState(State currentState)
{
    int new_c_left;
    int new_c_right;
    int new_m_left;
    int new_m_right;
    Side new_left ;
    Side new_right;
    
    //get current boat side
    int boat_side = currentState.leftSide.getBoatSide();
    if (boat_side==0)
    {
        //Boat is on LEFT SIDE
        //get new # of cannibals & missionaries based on action
        new_c_left = currentState.leftSide.getCannibals()- currentState.action.getCannibals();
        new_m_left = currentState.leftSide.getMissionaries()- currentState.action.getMissionaries();
        new_c_right = currentState.rightSide.getCannibals()+ currentState.action.getCannibals();
        new_m_right = currentState.rightSide.getCannibals()+ currentState.action.getCannibals();
        
        new_left= new Side(new_c_left, new_m_left,1);
        new_right = new Side(new_c_right, new_m_right,1);
    }
    else
    {   //Boat is on RIGHT SIDE
        //get new # of cannibals & missionaries based on action
        new_c_left = currentState.leftSide.getCannibals()+ currentState.action.getCannibals();
        new_m_left = currentState.leftSide.getMissionaries()+ currentState.action.getMissionaries();
        new_c_right = currentState.rightSide.getCannibals()- currentState.action.getCannibals();
        new_m_right = currentState.rightSide.getCannibals()- currentState.action.getCannibals();
        new_left= new Side(new_c_left, new_m_left,0);
        new_right = new Side(new_c_right, new_m_right,0);
    }
    //new state
    State new_state = new State(new_left,new_right);
    
    return new_state;
  
}

//check if meets the goal
bool check_goal_meet(State currentState)
{
    int c_left = currentState.leftSide.getCannibals();
    int m_left = currentState.leftSide.getMissionaries();
    
    if (c_left == 0 && m_left == 0)
    {
        return true;
    }
    return false;
    
    
}
void DFS (State* currentState)
{
    // get possible moves list for current state
    vector <Carry> possible_actions;
    getNextMoves(*currentState, possible_actions);
    
    // run for loop to go to each new state
    for (int i = 0; i< possible_actions.size(); i++)
    {
        //get next state
        currentState->next_state = get_nextState(*currentState);
        //check if goal is met
        bool check = check_goal_meet(*currentState->next_state);
        if (check == true)
        {
            return;
        }
        else
        {
            DFS(currentState->next_state);
        }
    }
}

int main()
{
    //initialize 1st state
    Side leftSide = new Side(3,3,0);
    Side rightSide = new Side(0,0,0);
    State root_state = new State (leftSide,rightSide);
    

    DFS(root_state);

}
