// 3

package com.wtm.spring_boot_wtm;
import com.wtm.spring_boot_wtm.model.Bar;
import com.wtm.spring_boot_wtm.repository.BarRepository;
import com.wtm.spring_boot_wtm.service.BarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BarServiceTest {

    @Mock
    private BarRepository barRepository;

    @InjectMocks
    private BarService barService;

    @Test
    void testAddBarIfNotExists_WhenBarDoesNotExist() {
        String placeId = "123";
        String name = "Test Bar";

        when(barRepository.existsByPlaceId(placeId)).thenReturn(false);

        barService.addBarIfNotExists(placeId, name);

        verify(barRepository).save(any(Bar.class));
    }

    @Test
    void testUpdateBusyness_WhenBarExists() {
        String placeId = "123";
        String busyness = "5";
        Bar bar = new Bar();
        bar.setPlaceId(placeId);

        when(barRepository.findByPlaceId(placeId)).thenReturn(Optional.of(bar));

        boolean result = barService.updateBusyness(placeId, busyness);

        assertTrue(result);
        assertEquals(5, bar.getBusyness());
        verify(barRepository).save(bar);
    }

    @Test
    void testGetBusynessByPlaceId_WhenBarExists() {
        String placeId = "123";
        Bar bar = new Bar();
        bar.setPlaceId(placeId);
        bar.setBusyness(10);

        when(barRepository.findByPlaceId(placeId)).thenReturn(Optional.of(bar));

        Optional<Integer> busyness = barService.getBusynessByPlaceId(placeId);

        assertTrue(busyness.isPresent());
        assertEquals(10, busyness.get());
    }
}
