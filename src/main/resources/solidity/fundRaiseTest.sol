pragma solidity ^0.4.24;

contract FundManagerTest {
  address public admin;

  constructor() public {
    admin = msg.sender;
  }
}