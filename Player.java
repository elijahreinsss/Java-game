
package javaapplication16;
public interface Player {
    String getName();
    int attack();
    int getHealth();
    void reduceHealth(int damage);
    void dodge();
}
