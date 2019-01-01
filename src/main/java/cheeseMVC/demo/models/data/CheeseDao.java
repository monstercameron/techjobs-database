package cheeseMVC.demo.models.data;

import cheeseMVC.demo.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by LaunchCode
 */
@Repository
@Transactional
public interface CheeseDao extends CrudRepository<Cheese, Integer> {
    
    Cheese getById(int Id);
    
}
