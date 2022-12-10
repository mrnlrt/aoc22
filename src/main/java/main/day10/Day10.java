package main.day10;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.String.join;

public class Day10 {
    List<Integer> values = new ArrayList<>();
    List<String> crt = new ArrayList<>();
    public int part1(List<String> instructions) {
        int x = 1;
        for(String instruction: instructions){
            values.add(x);

            if(!"noop".equals(instruction)){
                x = getNewRegisterValue(x, instruction);
                values.add(x);
            }
        }
        return 20 * values.get(18)
               + 60 * values.get(58)
               + 100 * values.get(98)
               + 140 * values.get(138)
               + 180 * values.get(178)
               + 220 * values.get(218);
    }

    private static int getNewRegisterValue(int x, String instruction) {
        String value = instruction.replace("addx ", "");
        return x + parseInt(value);
    }

    public void part2(List<String> instructions) {
        int x = 1;
        for(String instruction: instructions){
            addPixel(x);
            values.add(x);
            if(!"noop".equals(instruction)){
                addPixel(x);
                x = getNewRegisterValue(x, instruction);
                values.add(x);
            }
        }
        System.out.println(join(" ",crt.subList(0,40)));
        System.out.println(join(" ",crt.subList(40,80)));
        System.out.println(join(" ",crt.subList(80,120)));
        System.out.println(join(" ",crt.subList(120,160)));
        System.out.println(join(" ",crt.subList(160,200)));
        System.out.println(join(" ",crt.subList(200,240)));
    }

    private void addPixel(int spritePosition) {
        int pixelPosition = (values.size())%40;
        if(pixelPosition-1 <= spritePosition && pixelPosition + 1 >= spritePosition){
            crt.add("#");
        }
        else{
            crt.add(".");
        }
    }

}


