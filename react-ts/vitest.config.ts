import { defineConfig } from 'vitest/config';
import react from '@vitejs/plugin-react';

// @ts-ignore
export default defineConfig({
  plugins: [react()],
  test: {
    globals: true,
    environment: 'jsdom',
    setupFiles: './setupTests.ts',
    coverage: {
      provider: 'c8',
      reporter: ['text', 'json', 'html', 'json-summary'],
    },
  },
});