package serviceTest;

import com.arch.seckill.dto.SeckillDto;
import com.arch.seckill.service.SeckillService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by chenxiaobian on 16/12/22.
 */
public class SecKillTest extends AbstractSpringContextTest {

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testQueryAll(){
        List<SeckillDto> seckillDtoList = seckillService.queryAll();
        System.out.println(seckillDtoList);
    }

    @Test
    public void testFindBySecKillId(){
        SeckillDto seckillDto = seckillService.findById(2L);
        System.out.println(seckillDto);
    }



}
