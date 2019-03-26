package com.luban.facade;

public class FacadeMain {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        // 客户端调用现在更加清晰了
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
    }
}
