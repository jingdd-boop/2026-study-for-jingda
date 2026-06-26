const express = require('express');
const todoRoutes = require('./routes/todos');
const errorHandler = require('./middleware/errorHandler');

const app = express();

app.use(express.json());

app.get('/', (req, res) => {
  res.json({ name: '分层结构 Todo API', structure: 'routes → controllers → services' });
});

app.use('/api/todos', todoRoutes);

app.use((req, res) => {
  res.status(404).json({ code: 404, message: 'Not Found' });
});

app.use(errorHandler);

module.exports = app;
