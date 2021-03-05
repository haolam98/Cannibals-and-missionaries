//
//  State.cpp
//  cannibals_and_missionaries
//
//  Created by Hao Lam on 3/4/21.
//

#include "State.hpp"
#include "Carry.hpp"
#include "Side.hpp"
State::State()
{
   
}
State::State(Side left_side,Side right_side)
{
    this->leftSide = left_side;
    this->rightSide = right_side;
    this->action = new Carry();
    this->next_state = NULL;
}
