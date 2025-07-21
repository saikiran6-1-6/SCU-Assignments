"""
Added github link for this project
https://github.com/saikiran6-1-6/SCU-Assignments/tree/681f38b853fab2fca25bf5ba6bdd8ac4f1b734b7/Year1Term3/INFO6001/Assignment1
"""

import hashlib  # Module used For creating secure hashes
import time     # Module For calculating timestamps

# Class Block
class Block:
    """
    A single block on the blockchain.
    """
    def __init__(self, index, previous_hash, timestamp, data, proof):
        self.index = index  # Position of the current block in the chain
        self.previous_hash = previous_hash  # Hash of previous block
        self.timestamp = timestamp          # Timestamp of when the block was created
        self.data = data                    # The Data stored in the block
        self.proof = proof                  # Proof for mining (nonce)
        self.hash = self.calculate_hash()   # The hash for the current block

    def calculate_hash(self):
        # Returns a unique SHA-256 hash of the block's contents
        block_string = (
            str(self.index) +
            self.previous_hash +
            str(self.timestamp) +
            str(self.data) +
            str(self.proof)
        )
        return hashlib.sha256(block_string.encode()).hexdigest()

# Blockchain class
class Blockchain:
    """
    The whole blockchain.
    """
    def __init__(self):
        self.chain = [self.create_genesis_block()]  # First block of the blockchain which starts with the genesis block
        self.difficulty = 2  # Number of zeros the mined hash must start with.

    def create_genesis_block(self):
        # The first block or genesis block
        return Block(0, "0", time.time(), "Genesis Block", 0)

    def get_latest_block(self):
        # Returns last block in the chain
        return self.chain[-1]

    def proof_of_work(self, block):
        # Finds a proof so that hash starts with number of zeros specified above which is 2.
        block.proof = 0
        while True:
            block.hash = block.calculate_hash()
            if block.hash.startswith("0" * self.difficulty):
                break    # Success!
            block.proof += 1
            # Print progress every 1000 tries
            if block.proof % 1000 == 0:
                print(f"Still mining... proof: {block.proof}")

    def add_data(self, data):
        """
        Creates a new block with user's data, mines it, and adds it to the chain.
        """
        # Setting the correct previous_hash before mining!
        previous_hash = self.get_latest_block().hash
        new_block = Block(
            index = len(self.chain),
            previous_hash = previous_hash,
            timestamp = time.time(),
            data = data,
            proof = 0
        )
        self.proof_of_work(new_block)   # Mine the new block
        self.chain.append(new_block)    # Add block to chain (after mining)

    def is_chain_valid(self):
        """
        Checks if the blockchain is still valid i.e. not tampered.
        """
        for i in range(1, len(self.chain)):
            current = self.chain[i]
            previous = self.chain[i-1]

            # Checking if hash is correct
            if current.hash != current.calculate_hash():
                print(f"Block {current.index}: hash does not match calculation!")
                return False
            # Is the linkage correct?
            if current.previous_hash != previous.hash:
                print(f"Block {current.index}: previous hash does not match!")
                return False

        return True

# Input given by the user.
if __name__ == "__main__":
    blockchain = Blockchain()

    while True:
        print("\nPick an option:")
        print("1. Add a new block")
        print("2. Show the blockchain")
        print("3. Check if blockchain is valid")
        print("4. Exit")
        choice = input("Enter your choice: ").strip()
        if choice == "1":
            data = input("Enter data for the block: ")
            print("Mining new block...")
            blockchain.add_data(data)
            print("Block added!")
        elif choice == "2":
            for block in blockchain.chain:
                print(f"Block {block.index}")
                print(f"  Timestamp: {block.timestamp}")
                print(f"  Data: {block.data}")
                print(f"  Proof: {block.proof}")
                print(f"  Previous Hash: {block.previous_hash}")
                print(f"  Hash: {block.hash}\n")
        elif choice == "3":
            if blockchain.is_chain_valid():
                print("Blockchain is valid!")
            else:
                print("Blockchain is NOT valid!")
        elif choice == "4":
            print("Goodbye!")
            break
        else:
            print("Invalid option, try again.")
