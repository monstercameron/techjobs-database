package cheeseMVC.demo.models.data;

import cheeseMVC.demo.models.Category;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author earlcameron
 */
@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
    public Category findFirstByName(String name);
}
