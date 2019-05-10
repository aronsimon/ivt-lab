package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore first;
  private TorpedoStore second;

  @BeforeEach
  public void init(){
    first = mock(TorpedoStore.class);
    second = mock(TorpedoStore.class);

    this.ship = new GT4500(first, second);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(first.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    verify(first, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(first.fire(1)).thenReturn(true);
    when(second.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    //assertEquals(true, result);
    verify(first, times(1)).fire(1);
    verify(second, times(1)).fire(1);
  }

}
