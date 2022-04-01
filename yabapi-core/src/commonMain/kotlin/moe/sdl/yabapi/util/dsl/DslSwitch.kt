package moe.sdl.yabapi.util.dsl

public interface DslSwitchStatus

public enum class DslSwitch2Status : DslSwitchStatus { FIRST, SECOND; }

public enum class DslSwitch3Status : DslSwitchStatus { FIRST, SECOND, THIRD; }

public interface DslSwitch

public abstract class DslSwitch2 : DslSwitch {
    public infix fun set(status: DslSwitch2Status): Unit = when (status) {
        DslSwitch2Status.FIRST -> slot1()
        DslSwitch2Status.SECOND -> slot2()
    }

    internal abstract fun slot1()

    internal abstract fun slot2()
}

public abstract class DslSwitch3 : DslSwitch2() {
    public infix fun set(status: DslSwitch3Status): Unit = when (status) {
        DslSwitch3Status.FIRST -> slot1()
        DslSwitch3Status.SECOND -> slot2()
        DslSwitch3Status.THIRD -> slot3()
    }

    internal abstract fun slot3()
}
