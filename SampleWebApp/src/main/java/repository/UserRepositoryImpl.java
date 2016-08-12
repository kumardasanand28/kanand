package repository;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

	public User save(User user) {
		mongoOperation.save(user);
		return user;
	}

	public void remove(User user) {
		mongoOperation.remove(user);
	}

	public List<User> findAll() {
		return mongoOperation.findAll(User.class);
	}

	public User findUser(String email) {
		BasicQuery query = new BasicQuery("{ 'email':'"+email+"' }");
		return mongoOperation.findOne(query, User.class);
	}
	
	
	

}
