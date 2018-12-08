package br.com.util;

import java.security.InvalidKeyException;

public class Util {
	public static class Criptografia {

		private static char[] key = "cadeacarapaca".toCharArray();
		private static int[] sbox;
		private static final int SBOX_LENGTH = 256;
		private static final int TAM_MIN_CHAVE = 5;

		public static String decriptografa(final char[] msg) {
			return new String(criptografa(msg));
		}

		public static char[] criptografa(final char[] msg) {
			sbox = initSBox(key);
			char[] code = new char[msg.length];
			int i = 0;
			int j = 0;
			for (int n = 0; n < msg.length; n++) {
				i = (i + 1) % SBOX_LENGTH;
				j = (j + sbox[i]) % SBOX_LENGTH;
				swap(i, j, sbox);
				int rand = sbox[(sbox[i] + sbox[j]) % SBOX_LENGTH];
				code[n] = (char) (rand ^ (int) msg[n]);
			}
			return code;
		}

		private static int[] initSBox(char[] key) {
			int[] sbox = new int[SBOX_LENGTH];
			int j = 0;

			for (int i = 0; i < SBOX_LENGTH; i++) {
				sbox[i] = i;
			}

			for (int i = 0; i < SBOX_LENGTH; i++) {
				j = (j + sbox[i] + key[i % key.length]) % SBOX_LENGTH;
				swap(i, j, sbox);
			}
			return sbox;
		}

		private static void swap(int i, int j, int[] sbox) {
			int temp = sbox[i];
			sbox[i] = sbox[j];
			sbox[j] = temp;
		}

		public void setKey(String key) throws InvalidKeyException {
			if (!(key.length() >= TAM_MIN_CHAVE && key.length() < SBOX_LENGTH)) {
				throw new InvalidKeyException("Tamanho da chave deve ser entre "
						+ TAM_MIN_CHAVE + " e " + (SBOX_LENGTH - 1));
			}

			this.key = key.toCharArray();
		}

	}
}