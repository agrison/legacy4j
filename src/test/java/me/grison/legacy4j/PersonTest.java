package me.grison.legacy4j;

import me.grison.legacy4j.engine.LegacyFile;
import me.grison.legacy4j.engine.LegacyFiles;
import me.grison.legacy4j.engine.RecordConverter;
import me.grison.legacy4j.engine.mappers.EngineMappers;
import me.grison.legacy4j.test.Item;
import me.grison.legacy4j.test.Person;
import me.grison.legacy4j.test.PersonMapper;

public class PersonTest {
	public static void main(String[] args) {
		//FixedLengthFile<Item> file = new FixedLengthFile<Item>("positionnal.txt", Item.class);
		//FixedLengthFile<Item> file = new FixedLengthFile<Item>("positionnal.txt", Item.class);
        LegacyFile<Item> file = LegacyFiles.openFileReader("positionnal.txt", Item.class);
        EngineMappers.registerMapper(Person.class, new PersonMapper());
		//file.open();
        for (Item item: file) {
            System.out.println("> " + item + " /// " + RecordConverter.toString(item));
        }
		/*while (file.hasNext()) {
			Item item = file.next();
			System.out.println("> " + item);
			System.out.println("= " + RecordConverter.toString(item));
		} */

//		Item item = null;
//		while ((item = file.readNext()) != null) {
//			System.out.println("> " + item);
//			System.out.println("= " + RecordConverter.toString(item));
//		}
		file.close();
	}
}
