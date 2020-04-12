package test.lua;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class test {
    public static String script =
            "function r(q,...)\n"+
                    "	local a=arg\n"+
                    "	return a and a[2]\n"+
                    "end\n" +
                    "function s(q,...)\n"+
                    "	local a=arg\n"+
                    "	local b=...\n"+
                    "	return a and a[2],b\n"+
                    "end\n" +
                    "print( r(111,222,333),s(111,222,333) )";
    public static void main(String[] args) {
        System.out.println(script);

        // create an environment to run in
        Globals globals = JsePlatform.standardGlobals();

        // compile into a chunk, or load as a class
        LuaValue chunk = globals.load(script, "script");

        // The loaded chunk should be a closure, which contains the prototype.
        System.out.println( chunk.checkclosure().p );

        // The chunk can be called with arguments as desired.
        chunk.call(LuaValue.ZERO, LuaValue.ONE);
    }
}
