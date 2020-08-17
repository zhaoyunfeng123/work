//package redis;
//
//
//public class RedisUtil {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    //增
//    public void saveOne(String name, String value){
//        redisTemplate.opsForValue().set(name, value);
//    }
//    //删
//    public boolean deleteOne(String name){
//        return redisTemplate.delete(name);
//    }
//    //改
//    public Object updateOne(String name, String value){
//        //getAndSet()：设置键的字符串值，并返回旧值
//        return redisTemplate.opsForValue().getAndSet(name, value);
//    }
//    //查
//    public Object findOne(String name){
//        return redisTemplate.opsForValue().get(name);
//    }
//}
