//
//  Carry.hpp
//  cannibals_and_missionaries
//
//  Created by Hao Lam on 3/4/21.
//

#ifndef Carry_hpp
#define Carry_hpp

#include <stdio.h>
class Carry {
    
public:
    int numberOfCannibals;
    int numberOfMissionaries;
    
    Carry();
    Carry(int, int);
    bool isLegal (int ,int);
    int getCannibals();
    int getMissionaries();
    
};
#*endif /* Carry_hpp */
*