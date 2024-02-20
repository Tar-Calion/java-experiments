package tar.calion.java21.sealed;

public record SpotLight() implements Light {
    public void turnOn() {
        System.out.println("Spot light is on");
    }
}
