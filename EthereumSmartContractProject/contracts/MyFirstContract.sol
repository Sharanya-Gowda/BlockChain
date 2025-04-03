// SPDX-License-Identifier: MIT
pragma solidity ^0.6.10;

contract MyFirstContract {
    // State variable
    string public greeting;

    // Constructor
    constructor(string memory _greeting) public {
        greeting = _greeting;
    }

    // Function to set a new greeting
    function setGreeting(string memory _greeting) public {
        greeting = _greeting;
    }

    // Function to get the current greeting
    function getGreeting() public view returns (string memory) {
        return greeting;
    }
}
