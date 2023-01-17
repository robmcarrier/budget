module.exports = {
    root: true,
    extends: [
        'airbnb-typescript'
    ],
    plugins: ['import', 'prettier'],
    parserOptions: {
        project: './tsconfig.eslint.json',
    },
    rules: {
        "react/jsx-filename-extension": [0],
        "import/no-extraneous-dependencies": [0]
    }
};