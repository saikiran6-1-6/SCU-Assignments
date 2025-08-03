// SPDX-License-Identifier: MIT
pragma solidity ^0.8.30;

// Creating a Entity Manager contract to be able to create entities.
contract EntityManager {
    struct Entity {
        string name;
        string entityType; // "Sensor", "Official", "Citizen", etc.
        address entityAddress;
        bool registered;
    }

    mapping(address => Entity) internal entities;      // Address to Entity mapping is set to internal so it can be accessed internally only.
    address[] internal registeredEntities;             // List to store all entity addresses

    // Modifiers
    modifier onlyRegistered() {
        require(entities[msg.sender].registered, "Not a registered entity.");
        _;
    }

    modifier notRegistered() {
        require(!entities[msg.sender].registered, "Already registered.");
        _;
    }

    // Events
    event EntityRegistered(address indexed entityAddress, string name, string entityType);


    // Function to register entity.
    function registerEntity(string memory _name, string memory _entityType) public notRegistered {
        entities[msg.sender] = Entity({
            name: _name,
            entityType: _entityType,
            entityAddress: msg.sender,
            registered: true
        });
        registeredEntities.push(msg.sender);
        emit EntityRegistered(msg.sender, _name, _entityType);

    }


    // Function to check if the entity is registered.
    function isRegistered(address _user) public view returns (bool) {
        return entities[_user].registered;
    }


    // Function to return the registered entity.
    function getEntity(address _user)
        public
        view
        returns (string memory, string memory, address, bool)
    {
        Entity memory e = entities[_user];
        return (e.name, e.entityType, e.entityAddress, e.registered);
    }


    // Function to return all the registered entities.
    function getAllEntities() public view returns (address[] memory) {
        return registeredEntities;
    }
}
