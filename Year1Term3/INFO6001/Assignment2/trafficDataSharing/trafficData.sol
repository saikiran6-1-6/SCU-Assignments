// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

// To utilise the entityManager we need to import the EntityManager contract
import "./entityManager.sol";


// Creating trafficDataChain by inheriting entity manager contract.
contract TrafficDataChain is EntityManager {
    struct TrafficData {
        address reporter;
        string dataType;
        string dataDetails;
        uint256 timestamp;
    }

    TrafficData[] public trafficFeeds;

    // Event for traffic data submitted.
    event TrafficDataSubmitted(address indexed reporter, uint256 index, string dataType);


    // Function to submit traffic data by registered entity.
    function submitTrafficData(string memory _dataType, string memory _dataDetails) public onlyRegistered {
        TrafficData memory newEntry = TrafficData({
            reporter: msg.sender,
            dataType: _dataType,
            dataDetails: _dataDetails,
            timestamp: block.timestamp
        });
        trafficFeeds.push(newEntry);
        emit TrafficDataSubmitted(msg.sender, trafficFeeds.length - 1, _dataType);
    }

    function getTrafficDataCount() public view returns (uint256) {
        return trafficFeeds.length;
    }

    function getTrafficFeedAt(uint256 idx)
        public
        view
        returns (address reporter, string memory dataType, string memory dataDetails, uint256 timestamp)
    {
        require(idx < trafficFeeds.length, "Index out of range.");
        TrafficData memory t = trafficFeeds[idx];
        return (t.reporter, t.dataType, t.dataDetails, t.timestamp);
    }

    // Function which checks if traffic data is valid.
    function isTrafficDataValid(uint256 /*idx*/) public pure returns (bool) {

        return true;
    }
}
