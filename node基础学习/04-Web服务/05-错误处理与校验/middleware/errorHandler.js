/**
 * 全局错误处理中间件
 */
function errorHandler(err, req, res, next) {
  console.error('[Error]', err.message);

  // express-validator 错误
  if (err.array && typeof err.array === 'function') {
    return res.status(400).json({
      code: 400,
      message: '参数校验失败',
      errors: err.array(),
    });
  }

  const status = err.status || err.statusCode || 500;
  res.status(status).json({
    code: status,
    message: status === 500 ? '服务器内部错误' : err.message,
  });
}

module.exports = errorHandler;
