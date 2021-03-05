//
//  Carry.cpp
//  cannibals_and_missionaries
//
//  Created by Hao Lam on 3/4/21.
//

#include "Carry.hpp"

Carry::Carry()
{
    
}
Carry::Carry(int num_cannibals, int num_missionaries)
{
    if (isLegal(num_cannibals, num_missionaries))
    {
        this->numberOfCannibals = num_cannibals;
        this->numberOfMissionaries = num_missionaries;
    }
    else
    {
        // illegal action
    }
}
bool Carry:: isLegal(int num_cannibals, int num_missionaries)
{
    //check if action is legal
    return true;
}
int Carry:: getCannibals()
{
    return this->numberOfCannibals;
}
int Carry:: getMissionaries()
{
    return this->numberOfCannibals;
}

