package me.grison.legacy4j;

import junit.framework.TestCase;
import me.grison.legacy4j.engine.LegacyFile;
import me.grison.legacy4j.engine.LegacyFiles;
import me.grison.legacy4j.engine.RecordConverter;
import me.grison.legacy4j.engine.mappers.EngineMappers;
import me.grison.legacy4j.mapper.PersonMapper;
import me.grison.legacy4j.model.Item;
import me.grison.legacy4j.model.Person;
import org.junit.Test;

import java.io.File;

public class PersonTest {
    File personFile = new File(getClass().getResource("/person.txt").getFile());

    @Test
    public void testPerson() {
        LegacyFile<Item> file = LegacyFiles.openFileReader(personFile, Item.class);
        EngineMappers.registerMapper(Person.class, new PersonMapper());
        for (Item item: file) {
            System.out.println("> " + item + " /// " + RecordConverter.toString(item));
        }
		file.close();
	}
}
