//
//  Side.cpp
//  cannibals_and_missionaries
//
//  Created by Hao Lam on 3/4/21.
//

#include "Side.hpp"

Side::Side()
{
    
}
Side::Side(int num_cannibals, int num_missionaries, int boat_side)
{
    this->numberOfCannibals = num_cannibals;
    this->numberOfMissionaries = num_missionaries;
    this->boatSide = boat_side;
}
int Side:: getCannibals()
{
    return this->numberOfCannibals;
}
int Side:: getMissionaries()
{
    return this->numberOfCannibals;
}

int Side:: getBoatSide()
{
    return this->boatSide;
}

