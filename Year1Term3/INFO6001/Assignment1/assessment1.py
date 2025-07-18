import hashlib
import time
import time

class Block:
    def __init__(self, index, data, previous_hash):
        self.index = index  # Unique identifier for the block
        self.timestamp = time.time()  # To record time
        self.data = data  # To Store transaction/user data
        self.previous_hash = previous_hash  # Hash to the previous block
        self.hash = self.calculate_hash()  # To Store the hash of the current block

    def calculate_hash(self):
        """Return SHA 256 for the hash block."""
        block_string = str(self.index) + str(self.timestamp) + str(self.data) + str(self.previous_hash)
        return hashlib.sha256(block_string.encode()).hexdigest()
    

class Blockchain:
    def __init__(self):
        self.chain = [self.create_genesis_block()]
        self.difficulty = 4  # number of zeros for the hash to startwith

    def create_genesis_block(self):
        # First block in chain where previous hash is "0"
        return Block(0, "0", time.time(), "Genesis Block", 0)

    def get_latest_block(self):
        # Return last block in the chain
        return self.chain[-1]
    
    def add_block(self, new_block):
        new_block.previous_hash = self.get_latest_block().hash
        self.chain.append(new_block)

    def proof_of_work(self, block):
        block.proof = 0
        while True:
            block.hash = block.calculate_hash()
            if block.hash.startswith("0" * self.difficulty):
                break
            block.proof += 1

    def add_data(self, data):
        new_block = Block(
            len(self.chain),
            "0",
            time.time(),
            data,
            0
        )
        self.proof_of_work(new_block)
        self.add_block(new_block)

    def is_chain_valid(self):
        for i in range(1, len(self.chain)):
            current = self.chain[i]
            previous = self.chain[i-1]
            if current.hash != current.calculate_hash():
                print(f"Block {current.index}: hash does not match!")
                return False
            if current.previous_hash != previous.hash:
                print(f"Block {current.index}: is not linked to previous block!")
                return False
        return True

if __name__ == "__main__":
    blockchain = Blockchain()

    while True:
        print("\nChoose an option:")
        print("1. Add a new block")
        print("2. Show the blockchain")
        print("3. Check if blockchain is valid")
        print("4. Exit")
        choice = input("Enter your choice: ")
        if choice == "1":
            data = input("Enter data for the new block: ")
            print("Mining new block...")
            blockchain.add_data(data)
            print("Block added!")
        elif choice == "2":
            for block in blockchain.chain:
                print(f"Block {block.index}")
                print(f"  Timestamp: {block.timestamp}")
                print(f"  Data: {block.data}")
                print(f"  Proof: {block.proof}")
                print(f"  Prev Hash: {block.previous_hash}")
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
            print("Invalid option")
