package cn.thinking.common.basedao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:持久层交互通用类,以提供与Mybatis交互查询模板语句,业务模块的DAO类继承该类后进行交互查询
 * @update [修改人] [修改时间]
 * @version 1.0
 */
public class BaseDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/**
	 * 
	 * Discription:查询方法，不分页，直接返回结果集
	 * 
	 * @param statement
	 *            SqlMapper配置文件中insert SQL语句对应的“命名空间.sqlId”
	 * @return
	 */
	public List<?> selectList(String statement) {
		return sqlSession.selectList(statement);
	}

	/**
	 * 
	 * Discription:查询方法，不分页，直接返回结果集
	 * 
	 * @param statement
	 *            SqlMapper配置文件中insert SQL语句对应的“命名空间.sqlId”
	 * @param parameter
	 *            持久化对象
	 * @return
	 */
	public List<?> selectList(String statement, Object parameter) {
		return sqlSession.selectList(statement, parameter);
		
	}
	
	/**
	 * 
	 * Discription:查询方法，分页
	 * 
	 * @param statement
	 *            SqlMapper配置文件中insert SQL语句对应的“命名空间.sqlId”
	 * @param parameter
	 *            持久化对象
	 * @return
	 */
	public List<?> selectList(String statement, Object parameter, RowBounds rowBounds) {
		return sqlSession.selectList(statement, parameter,rowBounds);
	}

	/**
	 * 查询方法，查询单个对象
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public Object selectOne(String statement, Object parameter) {
		return sqlSession.selectOne(statement, parameter);
	}

	/**
	 * 
	 * Discription:新增操作
	 * 
	 * @param statement
	 *            SqlMapper配置文件中insert SQL语句对应的“命名空间.sqlId”
	 * @param parameter
	 * @return
	 */
	public <P> int insert(String statement, P parameter) {
		return sqlSession.insert(statement, parameter);
	}

	/**
	 * 
	 * Discription:修改操作
	 * 
	 * @param statement
	 *            SqlMapper配置文件中update SQL语句对应的“命名空间.sqlId”
	 * @param parameter
	 *            持久化对象
	 * @return
	 */
	public int update(String statement, Object parameter) {
		return sqlSession.update(statement, parameter);
	}

	/**
	 * 
	 * Discription:删除操作
	 *
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int delete(String statement, Object parameter) {
		return sqlSession.delete(statement, parameter);
	}

	/**
	 * 
	 * Discription:插入多行数据
	 *
	 * @param <P>
	 * @param statement
	 * @param list
	 * @return
	 */
	public <P> int insertMore(String statement, List<P> list) {
		// TODO Auto-generated method stub
		return sqlSession.insert(statement, list);
		//return 0;
	}
	/**
	 * 
	 * Discription:更新多行数据
	 *
	 * @param <P>
	 * @param statement
	 * @param list
	 * @return
	 */
	public <P> int updataMore(String statement, List<P> list) {
		// TODO Auto-generated method stub
		return sqlSession.update(statement,list);
	}
}