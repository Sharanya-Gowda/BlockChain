import unittest
from src.ctr_encryption import encrypt_ctr, decrypt_ctr
import os

class TestCTREncryption(unittest.TestCase):
    def setUp(self):
        self.key = os.urandom(16)  # AES-128 key
        self.transaction_data = "Test transaction data"

    def test_encryption_decryption(self):
        encrypted_data = encrypt_ctr(self.transaction_data, self
