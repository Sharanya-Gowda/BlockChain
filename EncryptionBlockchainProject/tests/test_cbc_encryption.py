import unittest
from src.cbc_encryption import encrypt_cbc, decrypt_cbc
import os

class TestCBCEncryption(unittest.TestCase):
    def setUp(self):
        self.key = os.urandom(16)  # AES-128 key
        self.transaction_data = "Test transaction data"

    def test_encryption_decryption(self):
        encrypted_data = encrypt_cbc(self.transaction_data, self.key)
        decrypted_data = decrypt_cbc(encrypted_data, self.key)
        self.assertEqual(self.transaction_data, decrypted_data)

if __name__ == '__main__':
    unittest.main()
