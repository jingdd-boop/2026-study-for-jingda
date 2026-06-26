const app = require('./app');

const PORT = process.env.PORT || 3006;

app.listen(PORT, () => {
  console.log(`✅ 分层结构 Demo: http://localhost:${PORT}`);
  console.log('   GET /api/todos');
});
