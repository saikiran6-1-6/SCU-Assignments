import hashlib
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
    

