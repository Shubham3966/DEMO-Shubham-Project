package MCM_Project.MCM.Unit_Test.service;

import MCM_Project.MCM.Entities.McmClass;
import MCM_Project.MCM.Repository.McmClassRepository;
import MCM_Project.MCM.exception.ResourceNotFoundException;
import MCM_Project.MCM.service.McmClassService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class McmClassServiceTest {

    @Mock
    private McmClassRepository mcmClassRepository;

    @InjectMocks
    private McmClassService mcmClassService;

    private McmClass mcmClass;

    @BeforeEach
    void setUp() {
        mcmClass = new McmClass();
        mcmClass.setId(1L);
        mcmClass.setName("Standard Consumption");
    }

    @Test
    void testGetAllMcmClasses() {
        List<McmClass> mcmClassList = Arrays.asList(mcmClass);
        when(mcmClassRepository.findAll()).thenReturn(mcmClassList);

        List<McmClass> result = mcmClassService.getAllMcmClasses();
        assertEquals(1, result.size());
    }

    @Test
    void testGetMcmClassById_Success() {
        when(mcmClassRepository.findById(1L)).thenReturn(Optional.of(mcmClass));

        McmClass result = mcmClassService.getMcmClassById(1L);
        assertNotNull(result);
        assertEquals("Standard Consumption", result.getName());
    }

    @Test
    void testGetMcmClassById_NotFound() {
        when(mcmClassRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> mcmClassService.getMcmClassById(1L));
    }

    @Test
    void testCreateMcmClass() {
        when(mcmClassRepository.save(mcmClass)).thenReturn(mcmClass);

        McmClass result = mcmClassService.createMcmClass(mcmClass);
        assertNotNull(result);
        assertEquals("Standard Consumption", result.getName());
    }

    @Test
    void testDeleteMcmClass_Success() {
        when(mcmClassRepository.existsById(1L)).thenReturn(true);
        doNothing().when(mcmClassRepository).deleteById(1L);

        assertDoesNotThrow(() -> mcmClassService.deleteMcmClass(1L));
    }

    @Test
    void testDeleteMcmClass_NotFound() {
        when(mcmClassRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> mcmClassService.deleteMcmClass(1L));
    }
}
