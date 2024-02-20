package tar.calion.java21.sealed;

public sealed interface Light permits SpotLight, FloodLight {
    void turnOn();
}
