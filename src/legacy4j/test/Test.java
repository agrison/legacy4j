package legacy4j.test;

import legacy4j.engine.FixedLengthFile;
import legacy4j.engine.RecordConverter;
import legacy4j.engine.mappers.EngineMappers;

public class Test {
    public static void main(String[] args) {
        FixedLengthFile<Item> file = new FixedLengthFile<Item>("positionnal.txt", Item.class);
        EngineMappers.registerMapper(Person.class, new PersonMapper());
        try {
            for (Item item : file) {
                System.out.println("> " + item);
                System.out.println("= " + RecordConverter.toString(item));
            }
        } finally {
            file.close();
        }
    }
}
