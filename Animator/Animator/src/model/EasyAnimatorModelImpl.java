package model;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import util.AnimationBuilder;
import util.AnimationReader;


/**
 * A class that represents an implementation of the easy animator model. Each EasyAnimatorModelImpl
 * represents an animation that holds a list of shapes and the duration for how long the animation
 * shall run.
 */
public class EasyAnimatorModelImpl implements EasyAnimatorModel {

  public int view_window_x;
  public int view_window_y;
  public int view_window_width;
  public int view_window_height;
  List<Shape> shapes;
  EasyAnimatorModel model;

  /**
   * Constructor for EasyAnimatorModelImpl. Parameters are the lists of shape and the duration
   * length of the animation.
   *
   * @param shapes The list of shapes to be included in the animation.
   * @throws IllegalArgumentException if the duration or list is invalid.
   */
  public EasyAnimatorModelImpl(List<Shape> shapes) throws IllegalArgumentException {
    this.shapes = shapes;
    if (shapes == null) {
      throw new IllegalArgumentException("Null or invalid value for ModelImplConstructor.");
    }
    this.view_window_x = 10;
    this.view_window_y = 10;
    this.view_window_width = 600;
    this.view_window_height = 400;
  }

  /**
   * Secondary constructor for EasyAnimatorModelImpl. Parameter is the string infile.
   *
   * @param infile input file to use for animation.
   * @throws FileNotFoundException thrown if file does not exist.
   */
  public EasyAnimatorModelImpl(String infile) throws FileNotFoundException {

    this.shapes = new ArrayList<>();

    AnimationBuilder builder = new Builder(this);

    File file = new File(infile);
    Readable reader = new FileReader(file);
    this.model = (EasyAnimatorModel) AnimationReader.parseFile(reader, builder);
    this.shapes = this.model.getShapes();

  }

  @Override
  public List<Shape> getShapes() {

    List<Shape> shapes_copy = new ArrayList<>();
    for (Shape shape : this.shapes) {
      shapes_copy.add(shape.copy());
    }

    return shapes_copy;
    //return this.shapes;
  }

  @Override
  public List<Shape> getExactShapes() {
    return this.shapes;
  }

  @Override
  public void changeShape(String shapeInit, Shape shapeFinal) throws IllegalArgumentException {
    if (shapeInit == null || shapeFinal == null) {
      throw new IllegalArgumentException("changeShape values cannot be null.");
    }
    for (Shape shape : this.shapes) {
      if (shape.getName().equals(shapeInit)) {
        shape = shapeFinal;
      }
    }
  }

  @Override
  public void addMove(String name, PositionChange positionChange) throws IllegalArgumentException {
    if (name == null || positionChange == null) {
      throw new IllegalArgumentException("addMove values cannot be null.");
    }
    if (positionChange.getStartTime() < 0
            || positionChange.getEndTime() <= 0) {
      throw new IllegalArgumentException("Time value must be valid.");
    }
    for (Shape shape : this.shapes) {
      if (shape.getName().equals(name)) {
        shape.addPositionChange(positionChange);
      }
    }
  }

  @Override
  public void addResize(String name, SizeChange sizeChange) throws IllegalArgumentException {
    if (name == null || sizeChange == null) {
      throw new IllegalArgumentException("addResize values cannot be null.");
    }
    for (Shape shape : this.shapes) {
      if (shape.getName().equals(name)) {
        shape.addSizeChange(sizeChange);
      }
    }
  }

  @Override
  public void addColorChange(String name, ColorChange colorChange) throws IllegalArgumentException {
    if (name == null || colorChange == null) {
      throw new IllegalArgumentException("addColorChange values cannot be null.");
    }
    if (colorChange.getTime() < 0) {
      throw new IllegalArgumentException("Time value must be valid.");
    }

    for (Shape shape : this.shapes) {
      if (shape.getName().equals(name)) {
        shape.addColorChange(colorChange);
      }
    }
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null.");
    }
    this.shapes.add(shape);
  }

  @Override
  public String describe() {
    StringBuilder str = new StringBuilder("Shapes:\n\n");
    for (Shape shape : this.shapes) {
      str.append(String.format("Name: %s\nType: %s\n%s\nAppears at %.2f\nDisappears at %.2f\n\n",
              shape.getName(), shape.getType(), shape.toString(), shape.getAppears(),
              shape.getDisappears()));
    }
    for (Shape shape : this.shapes) {
      for (PositionChange positionChange : shape.getPositionChanges()) {
        str.append(String.format("Shape %s moves from %s to %s from %.2f to %.2f\n",
                shape.getName(), positionChange.getFrom().toString(),
                positionChange.getTo().toString(),
                positionChange.getStartTime(), positionChange.getEndTime()));
      }
    }
    for (Shape shape : this.shapes) {
      for (SizeChange sizeChange : shape.getSizeChanges()) {
        str.append(String.format("Shape %s scales by %.2f from %.2f to %.2f\n",
                shape.getName(), sizeChange.getFactor(),
                sizeChange.getStartTime(), sizeChange.getEndTime()));
      }
    }
    for (Shape shape : this.shapes) {
      for (ColorChange colorChange : shape.getColorChanges()) {
        str.append(String.format("Shape %s changes color to %s at %.2f\n",
                shape.getName(), ColorName.colorName(colorChange.getColor()),
                colorChange.getTime()));
      }
    }
    return str.toString();
  }

  @Override
  public EasyAnimatorModelImpl copy() {
    return new EasyAnimatorModelImpl(shapes);
  }

  @Override
  public void setReference(String name, Point2D reference) {
    for (Shape shape : this.shapes) {
      if (shape.getName().equals(name)) {
        shape.setReference(reference);
      }
    }
  }

  @Override
  public void setColor(String name, Color color) {
    for (Shape shape : this.shapes) {
      if (shape.getName().equals(name)) {
        shape.setColor(color);
      }
    }
  }

  @Override
  public double getDurationTicks() {
    double largest_tick = 0;
    for (Shape shape : this.shapes) {
      for (PositionChange positionChange : shape.getPositionChanges()) {
        if (positionChange.getEndTime() > largest_tick) {
          largest_tick = positionChange.getEndTime();
        }
      }
    }
    return largest_tick;
  }

  @Override
  public void setViewWindowX(int x) {
    this.view_window_x = x;
  }

  @Override
  public void setViewWindowY(int y) {
    this.view_window_y = y;
  }

  @Override
  public void setViewWindowWidth(int width) {
    this.view_window_width = width;
  }

  @Override
  public void setViewWindowHeight(int height) {
    this.view_window_height = height;
  }

  @Override
  public void setShapes(List<Shape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Public static class that represents the Builder and implements the AnimationBuilder Interface.
   */
  public static final class Builder implements AnimationBuilder<EasyAnimatorModel> {

    private EasyAnimatorModel model;

    Builder(EasyAnimatorModel model) {
      this.model = model;
    }

    @Override
    public EasyAnimatorModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<EasyAnimatorModel> setBounds(int x, int y, int width, int height) {
      //this.model.setViewWindowX(x);
      //this.model.setViewWindowY(y);
      //this.model.setViewWindowWidth(width);
      //this.model.setViewWindowHeight(height);
      this.model.setViewWindowX(10);
      this.model.setViewWindowY(10);
      this.model.setViewWindowWidth(1500);
      this.model.setViewWindowHeight(1000);
      return null;
    }


    @Override
    public AnimationBuilder<EasyAnimatorModel> declareShape(String name, String type) {

      List<SizeChange> sizeChanges = new ArrayList<>();
      sizeChanges.add(new SizeChange(1, 0, 0));

      List<PositionChange> positionChanges = new ArrayList<>();
      positionChanges.add(new PositionChange(new Point2D(0, 0), 100,
              1, 0, 1));

      List<ColorChange> colorChanges = new ArrayList<>();
      colorChanges.add(new ColorChange(Color.BLACK, 0));

      Shape shape = null;
      if (type.equals("circle") || type.equals("ellipse")) {
        shape = new Circle(name, Color.YELLOW, 0, 0, 1,
                positionChanges, sizeChanges, colorChanges, 0, 0);
      }
      if (type.equals("rectangle")) {
        shape = new Rectangle(name, Color.BLACK, 0, 0, 100, 100,
                positionChanges, sizeChanges, colorChanges, 0, 0);
      }

      try {
        this.model.addShape(shape);
      } catch (NullPointerException e) {
        System.out.println("A shape in your animation is not currently supported.");
      }

      return null;
    }

    @Override
    public AnimationBuilder<EasyAnimatorModel> addMotion(String name,
                                                         int t1,
                                                         int x1,
                                                         int y1,
                                                         int w1,
                                                         int h1,
                                                         int r1,
                                                         int g1,
                                                         int b1,
                                                         int t2,
                                                         int x2,
                                                         int y2,
                                                         int w2,
                                                         int h2,
                                                         int r2,
                                                         int g2,
                                                         int b2) {

      if (this.model.getShapes() != null) {

        Point2D position_init = new Point2D(x1, y1);
        Color color_init = new Color(r1, g1, b1);

        List<Shape> shapes = new ArrayList<>();

        for (Shape shape : this.model.getShapes()) {

          // Remove initializer move
          shape.removeMove(name, new Point2D(0, 0), 100, 1, 0, 1);

          shape.setReference(position_init);
          shape.setColor(color_init);

          ShapeVisitor visitor = new ShapeVisitor(w1, h1, r1);
          shape.accepts(visitor);
          shapes.add(shape);
        }
        this.model.setShapes(shapes);


        //this.model.setReference(name, position_init);

        if (t1 != t2) {   // added by CCF, needed for parsing toh-12.txt
          PositionChange move = new PositionChange(position_init, x2, y2, t1, t2);
          this.model.addMove(name, move);
        }

        //Color color_init = new Color(r1, g1, b1);
        //this.model.setColor(name, color_init);
        Color color_final = new Color(r2, g2, b2);
        ColorChange colorChange = new ColorChange(color_final, t2);
        this.model.addColorChange(name, colorChange);

        double factor = (double) (h2 / h1);
        SizeChange sizeChange = new SizeChange(factor, t1, t2);
        this.model.addResize(name, sizeChange);
      }
      return null;
    }
  }

}
