/**
 * JWT 鉴权中间件
 */
const jwt = require('jsonwebtoken');

function verifyToken(req, res, next) {
  const auth = req.headers.authorization;
  const token = auth?.startsWith('Bearer ') ? auth.slice(7) : null;

  if (!token) {
    return res.status(401).json({ code: 401, message: '未登录，请先 POST /api/login' });
  }

  try {
    req.user = jwt.verify(token, process.env.JWT_SECRET || 'demo-jwt-secret-change-in-production');
    next();
  } catch {
    res.status(401).json({ code: 401, message: 'token 无效或已过期' });
  }
}

module.exports = verifyToken;
