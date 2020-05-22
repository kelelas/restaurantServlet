import com.kelelas.model.dao.DaoFactory;
import com.kelelas.model.dao.DishDao;
import com.kelelas.model.dao.HistoryDao;
import com.kelelas.model.entity.Dish;
import com.kelelas.model.entity.History;
import com.kelelas.model.entity.Ingredient;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    DaoFactory factory = DaoFactory.getInstance();
    DishDao dishDao = factory.createDishDao();
    HistoryDao historyDao = factory.createHistoryDao();

    @Test
    public void test(){
        List<Dish> result = dishDao.findAll();
        for (Dish dish : result){
            System.out.println(dish.getNameUkr());
            for (Ingredient ingredient : dish.getIngredients()){
                System.out.println(ingredient.getNameUkr());
            }
        }
    }
    @Test
    public void test2(){
        try {
            Dish result = dishDao.findById(1).orElseThrow(Exception::new);
            for (Ingredient ingredient : result.getIngredients()){
                System.out.println(ingredient.getNameUkr());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test3(){
        try {
            Dish result = dishDao.findById(1).orElseThrow(Exception::new);
            Dish result2 = dishDao.findById(2).orElseThrow(Exception::new);
            List<Dish> list = new ArrayList<>();
            list.add(result);
            list.add(result2);
            History history = new History.Builder()
                    .id(1)
                    .price(1)
                    .status(1)
                    .userId(0)
                    .date(LocalDateTime.now())
                    .dishes(list)
                    .build();
            historyDao.create(history);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
