package Tests.Repository;

import Repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    private Repository<Integer, String> repo;

    @BeforeEach
    void setUp() {
        repo = new Repository<Integer,  String>();
    }

    @Test
    void testAddAndGet() {
        repo.add(1, "Alice");
        repo.add(2, "Bob");

        assertEquals("Alice", repo.get(1));
        assertEquals("Bob", repo.get(2));
    }

    @Test
    void testAddDuplicateId() {
        repo.add(1, "Alice");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> repo.add(1, "Charlie"));
        assertEquals("The ID already exists", ex.getMessage());
    }

    @Test
    void testDelete() {
        repo.add(1, "Alice");
        repo.delete(1);
        assertNull(repo.get(1));
    }

    @Test
    void testDeleteNonExistentId() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> repo.delete(99));
        assertEquals("The ID doesn't exists", ex.getMessage());
    }

    @Test
    void testUpdate() {
        repo.add(1, "Alice");
        repo.update(1, "Charlie");
        assertEquals("Charlie", repo.get(1));
    }

    @Test
    void testUpdateNonExistentId() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> repo.update(99, "X"));
        assertEquals("The ID doesn't exists", ex.getMessage());
    }

    @Test
    void testGetAllAndGetList() {
        repo.add(1, "Alice");
        repo.add(2, "Bob");

        ArrayList<String> list = repo.getlist();
        assertEquals(2, list.size());
        assertTrue(list.contains("Alice"));
        assertTrue(list.contains("Bob"));

        Iterable<String> all = repo.getAll();
        ArrayList<String> allList = new ArrayList<>();
        all.forEach(allList::add);
        assertEquals(2, allList.size());
        assertTrue(allList.contains("Alice"));
        assertTrue(allList.contains("Bob"));
    }

    @Test
    void testGetNonExistentId() {
        assertNull(repo.get(999));
    }
}
