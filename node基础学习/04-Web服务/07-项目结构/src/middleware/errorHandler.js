function errorHandler(err, req, res, next) {
  console.error('[Error]', err.message);
  const status = err.status || 500;
  res.status(status).json({
    code: status,
    message: err.status ? err.message : '服务器错误',
  });
}

module.exports = errorHandler;
