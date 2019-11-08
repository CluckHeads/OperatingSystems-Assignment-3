import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Monitor
public class Garden {

  // Initialize variables
  int holesDug = 0, holesPlanted = 0, holesFilled = 0, holesEmpty = 0;

  // Initialize locks and conditions
  final Lock l = new ReentrantLock();
  final Condition digCond = l.newCondition();
  final Condition plantCond = l.newCondition();
  final Condition fillCond = l.newCondition();

  // START waitToDig()---------------------------------------------------------
  public void waitToDig() throws InterruptedException {
    l.lock();
    try {
      // Jordan will wait if there are five unfilled holes
      if (holesEmpty == 5) {
        System.out.println("Jordan is waiting to dig a hole.");
        digCond.await();
      }
//      while (holesDug > 5)
//        digCond.await();
    } finally {
      l.unlock();
    }

  }

  // START dig()---------------------------------------------------------------
  public void dig() throws InterruptedException {
    l.lock();
    try {
      holesDug++;
      holesEmpty++;
      System.out.println("Jordan dug a hole.\t\t\t\t\t\t\t\t\t" + holesDug);
      fillCond.signal();

    } finally {
      l.unlock();
    }

  } // END dig()


  // START waitToPlant()-------------------------------------------------------
  public void waitToPlant() throws InterruptedException {
    l.lock();
    try {
      // Charles will wait if all holes have plants in them
      if (holesDug == holesPlanted) {
        plantCond.await();
      }

    } finally {
      l.unlock();
    }
  } // END waitToPlant()


  // START plant()-------------------------------------------------------------
  public void plant() throws InterruptedException {
    l.lock();
    try {
      digCond.signal();

      if (holesDug != 0) { // Cannot plant when there is no hole
        holesPlanted++;
        holesEmpty--;
        System.out.println("Charles planted a hole.\t\t\t\t\t\t" + holesPlanted);
      }


    } finally {
      l.unlock();
    }
  } // END plant()


  // START waitToFill()--------------------------------------------------------
  public void waitToFill() throws InterruptedException {
    l.lock();
    try {

      // Tracy will wait if all holes are either empty or already filled
      if (holesFilled == holesPlanted || holesEmpty == 0)
        fillCond.await();

//      if (holesDug == 10 || holesPlanted == 0)
//        System.out.println("Tracy is waiting to fill a hole.");
//
//      while (holesFilled == holesPlanted)
//        fillCond.await();

    } finally {
      l.unlock();
    }
  } // END waitToFill


  // START fill()--------------------------------------------------------------
  public void fill() throws InterruptedException {
    l.lock();
    try {
      //while (holesFilled < 11) {
      if (holesDug != 0 && holesPlanted != 0) {
        holesFilled++;
        System.out.println("Tracy filled a hole.\t\t\t\t\t\t\t" + holesFilled);
      }
      //}
      plantCond.signal();
    } finally {
      l.unlock();
    } // end finally

  } // END dill()

} // END Garden class
