//
//  State.hpp
//  cannibals_and_missionaries
//
//  Created by Hao Lam on 3/4/21.
//

#ifndef State_hpp
#define State_hpp

#include <stdio.h>
#include "Carry.hpp"
#include "Side.hpp"
using namespace std;
class State {
    
public:
    Carry action;
    Side leftSide;
    Side rightSide;
    State* next_state;
    
    //constructor
    State();
    State(Side,Side,Carry,State);
    
};

#endif /* State_hpp */
