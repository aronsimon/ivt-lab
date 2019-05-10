package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  TorpedoStore first;
  TorpedoStore second;


  @BeforeEach
  public void init(){
    first = mock(TorpedoStore.class);
    second = mock(TorpedoStore.class);

    this.ship = new GT4500(first, second);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    //when(first.fire(1)).thenReturn(true);
    when(first.isEmpty()).thenReturn(false);
    // Act
    /*boolean result = */ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    verify(first, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    //when(first.fire(1)).thenReturn(true);
    //when(second.fire(1)).thenReturn(true);
    when(first.isEmpty()).thenReturn(false);
    when(second.isEmpty()).thenReturn(false);
    // Act
    /*boolean result = */ship.fireTorpedo(FiringMode.ALL);

    // Assert
    //assertEquals(true, result);
    verify(first, times(1)).fire(1);
    verify(second, times(1)).fire(1);
  }


   // 1.) Fire single while both torpedostores are empty, expected: false
   // 2.) Fire single while only one torpedostore has bullet (the one in order), expected: true
   // 3.) Fire single while only one torpedostore has bullet (the one that should rest), expected: true
   // 4.) Fire all while only one torpedostore has bullet, expected: false
   // 5.) Fire all while both torpedostores are empty, expected: false

   @Test
 public void fireTorpedo_single_empty() {
   //Arrange
   when(first.isEmpty()).thenReturn(true);
   when(second.isEmpty()).thenReturn(true);
   when(first.fire(1)).thenReturn(false);
   when(second.fire(1)).thenReturn(false);

   //Act
   ship.fireTorpedo(FiringMode.SINGLE);

   //Verify
   verify(first,times(0)).fire(1);
   verify(second,times(0)).fire(1);
 }

 @Test
 public void fireTorpedo_single_oneBullet_in_order() {
   //Arrange
   ship.setWasPrimaryFiredLast(false);
   when(first.isEmpty()).thenReturn(false);
   when(second.isEmpty()).thenReturn(true);
   when(first.fire(1)).thenReturn(true);
   when(second.fire(1)).thenReturn(false);

   //Act
   ship.fireTorpedo(FiringMode.SINGLE);

   //Verify
   verify(first,times(1)).fire(1);
   verify(second,times(0)).fire(1);
 }

 @Test
 public void fireTorpedo_single_oneBullet_out_of_order() {
   //Arrange
   ship.setWasPrimaryFiredLast(false);
   when(first.isEmpty()).thenReturn(true);
   when(second.isEmpty()).thenReturn(false);
   when(first.fire(1)).thenReturn(false);
   when(second.fire(1)).thenReturn(true);

   //Act
   ship.fireTorpedo(FiringMode.SINGLE);

   //Verify
   verify(first,times(0)).fire(1);
   verify(second,times(1)).fire(1);
 }

 @Test
 public void fireTorpedo_all_oneBullet() {
   //Arrange
   when(first.isEmpty()).thenReturn(false);
   when(second.isEmpty()).thenReturn(true);
   when(first.fire(1)).thenReturn(true);
   when(second.fire(1)).thenReturn(false);

   //Act
   ship.fireTorpedo(FiringMode.ALL);

   //Verify
   verify(first,times(0)).fire(1);
   verify(second,times(0)).fire(1);
 }

 @Test
 public void fireTorpedo_all_empty() {
   //Arrange
   when(first.isEmpty()).thenReturn(true);
   when(second.isEmpty()).thenReturn(true);
   when(first.fire(1)).thenReturn(false);
   when(second.fire(1)).thenReturn(false);

   //Act
   ship.fireTorpedo(FiringMode.ALL);

   //Verify
   verify(first,times(0)).fire(1);
   verify(second,times(0)).fire(1);
 }

 @Test
 public void check_PrimaryFiredLast() {
   //Arrange
   ship.setWasPrimaryFiredLast(false);
   when(first.isEmpty()).thenReturn(false);
   when(second.isEmpty()).thenReturn(false);
   when(first.fire(1)).thenReturn(true);
   when(second.fire(1)).thenReturn(true);

   //Act
   ship.fireTorpedo(FiringMode.SINGLE);
   boolean result = ship.getWasPrimaryFiredLast();

   //Verify
   assertEquals(true,result,"PrimaryFiredLast");
 }

 @Test
 public void check_notPrimaryFiredLast() {
   //Arrange
   ship.setWasPrimaryFiredLast(true);
   when(first.isEmpty()).thenReturn(false);
   when(second.isEmpty()).thenReturn(false);
   when(first.fire(1)).thenReturn(true);
   when(second.fire(1)).thenReturn(true);

   //Act
   ship.fireTorpedo(FiringMode.SINGLE);
   boolean result = ship.getWasPrimaryFiredLast();

   //Verify
   assertEquals(false,result,"PrimaryNotFiredLast");
 }

}
