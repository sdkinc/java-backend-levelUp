package homework_001;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class SqlGenerator_try1 {

  public String generateTable(Class<?> tableClass){
    try {
      Constructor<?> constructor = (Constructor<?>) tableClass.getConstructor(); // получаем пустой конструктор для произвольного класса

      Object result; // создаю экземпляр класса
      result = constructor.newInstance();
      String fields =processAnnotations(result);
      return "CREATE TABLE "+tableClass.getName().replace(tableClass.getPackageName()+".","")+" (\n"+fields+")";
    } catch (ReflectiveOperationException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private <T> String processAnnotations(T object) {
    StringBuilder resultFields = new StringBuilder();
    Class<T> aClass = (Class<T>) object.getClass(); // получаем класс исходного объекта

    Field[] fields = aClass.getDeclaredFields(); // получили все поля
    int i=0;
    for (Field field : fields) {

      Varchar varchar = field.getDeclaredAnnotation(Varchar.class); // получаем аннотацию над полем, если она есть
      String varcharSize = "";
      if(varchar!=null){
        varcharSize = "("+varchar.maxLength()+")";
      }

      PrimaryKey primaryKey = field.getDeclaredAnnotation(PrimaryKey.class); // получаем аннотацию над полем, если она есть
      boolean isPrimaryKey = false;
      if (primaryKey != null) { // если аннотация над полем есть
        isPrimaryKey=true;
      }

      Unique unique = field.getDeclaredAnnotation(Unique.class); // получаем аннотацию над полем, если она есть
      boolean isUnique = false;
      if (unique !=null){
        isUnique=true;
      }

      NotNull notNull = field.getDeclaredAnnotation(NotNull.class); // получаем аннотацию над полем, если она есть
      boolean isNotNull = false;
      if (notNull !=null){
        isNotNull=true;
      }
      Result result = new Result(varcharSize, isPrimaryKey, isUnique, isNotNull);

      String res = field.getName()+ " "+translateTypeIntoSQLType(field.getType().getName())+ result.varcharSize()
          +" "+ (result.isPrimaryKey() ?"PRIMARY KEY ":"")+
          (result.isUnique() ?"UNIQUE ":"")+(result.isNotNull() ?"NOT NULL ":"");
      if(i!=0){
        resultFields.append(",");
      }
      resultFields.append(res);
      resultFields.append("\n");
      i++;
    }
    return resultFields.toString();
  }

  private record Result(String varcharSize, boolean isPrimaryKey, boolean isUnique, boolean isNotNull) {

  }

  private <type> String translateTypeIntoSQLType(String typeName){
    return switch (typeName) {
      case "long", "java.lang.Integer","java.lang.Long"  -> "INT";
      case "boolean" -> "BOOLEAN";
      case "java.lang.String" -> "VARCHAR";
      default -> "";
    };
  }

}
