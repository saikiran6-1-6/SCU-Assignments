## Smart City Traffic Data Sharing – Blockchain Prototype

This project is a Solidity-based prototype for secure and transparent traffic data sharing in a smart city environment. It features two modular smart contracts:

-   **EntityManager:**  Manages the registration of participants, including citizens, sensors, and officials. Each entity registers with a unique name and type, and duplicate registrations are prevented. The contract maintains a list of registered entities for identity management and access control.
    
-   **TrafficDataChain:**  Extends the EntityManager and allows only registered entities to submit traffic reports (such as accidents or congestion). Each report includes the type of incident, detailed description, reporter address, and timestamp, simulating the addition of new blocks to a blockchain. Data submissions are stored immutably and events are logged, providing transparency and traceability.
    

The system enforces strict registration and submission rules to maintain data integrity and prevent unauthorized access. Query functions support retrieval of registered users and submitted traffic data, ensuring open access for trusted participants. The design is modular and easily adaptable for broader smart city or smart environment applications.

**Key Features:**

-   Decentralized and transparent management of traffic data.
    
-   Role-based access: only registered users can contribute data.
    
-   Tamper-resistant data storage leveraging blockchain principles.
    
-   Ready for extension to other smart city domains (e.g., environmental data, energy trading).
    

You can deploy and test the contracts using the Remix IDE, taking advantage of multiple test accounts to simulate different users and scenarios.
