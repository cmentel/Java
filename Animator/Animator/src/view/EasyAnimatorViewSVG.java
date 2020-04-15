package view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.ColorChange;
import model.EasyAnimatorModel;
import model.EasyAnimatorModelImpl;
import model.PositionChange;
import model.Shape;
import model.SizeChange;

/**
 * EasyAnimatorViewSVG Class that represents the SVG view for a model. It implements the
 * EasyAnimatorView and acts as the spec print out for the SVG Definition of animation. Each
 * instance of this class represents an SVG view of an animation.
 */
public class EasyAnimatorViewSVG implements EasyAnimatorView {

  private final EasyAnimatorModel model;
  private String str;
  private String outfile;

  /**
   * EasyAnimatorViewSVG constructor that takes one parameter that is the model. Each instance
   * represents an SVG animation from the model provided.
   *
   * @param model the model to be animated.
   * @throws IllegalArgumentException if the model is null.
   */
  public EasyAnimatorViewSVG(EasyAnimatorModelImpl model, String outfile)
          throws IllegalArgumentException {
    if (model == null || outfile == null) {
      throw new IllegalArgumentException("Model to be animated cannot be null.");
    }

    try {
      this.outfile = outfile;

      this.model = model;
      str = "<?xml version=\"1.0\" standalone=\"no\"?>\n"
              + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n"
              + "\t\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
              + "<svg width=\"8cm\" height=\"3cm\"  viewBox=\"0 0 800 300\"\n"
              + "\txmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
              + "\t<desc> Easy Animation </desc>\n";
    } catch (Exception e) {
      throw new IllegalArgumentException("Unable to locate file.");
    }
  }

  @Override
  public void animate(int tempo) throws FileNotFoundException {
    if (tempo == 0) {
      throw new IllegalArgumentException("Tempo cannot be zero.");
    }

    StringBuilder tmp = new StringBuilder();
    for (Shape shape : model.getShapes()) {
      tmp.append("\t<").append(shape.getType()).append(" x=\"").append(
              shape.getReference().getX()).append("\"").append(
              " y=\"").append(shape.getReference().getY()).append(
              "\"").append(shape.getSpecsString()).append(
              " fill= ").append(shape.getColor(0)).append(">\n");
      for (PositionChange move : shape.getPositionChanges()) {
        tmp.append("\t\t<animate attributeName=\"x\" attributeType=XML begin=\"").append(
                move.getStartTime()).append("s\"").append(" dur= ").append(
                move.getEndTime() - move.getStartTime()).append("s").append(
                " from=").append(move.getFrom().getX()).append(
                " to= ").append(move.getTo().getX()).append("/>\n");
        tmp.append("\t\t<animate attributeName=\"y\" attributeType=XML begin=\"").append(
                move.getStartTime()).append("s\"").append(" dur= ").append(
                move.getEndTime() - move.getStartTime()).append("s").append(
                " from=").append(move.getFrom().getY()).append(" to= ").append(
                move.getTo().getY()).append("/>\n");
      }

      for (SizeChange resize : shape.getSizeChanges()) {
        if (shape.getType().equals("rect")) {
          tmp.append("\t\t<animate attributeName=\"width\" attributeType=\"XML\" begin=\"0s\" ")
                  .append("dur=\"2s\" fill=\"freeze\" from=").append(
                  shape.getSpecs().get(0)).append(" to= ").append(shape.getSpecs().get(0)
                  * resize.getFactor()).append("/>\n");
          tmp.append("\t\t<animate attributeName=\"height\" attributeType=\"XML\" begin=\"0s\" ")
                  .append("dur=\"2s\" fill=\"freeze\" from=").append(shape.getSpecs().get(1))
                  .append(" to= ").append(shape.getSpecs().get(1) * resize.getFactor())
                  .append("/>\n");
        }
        if (shape.getType().equals("circle")) {
          tmp.append("\t\t<animate attributeName=\" radius\" attributeType=\"XML\" begin=\"0s\" ")
                  .append("dur=\"2s\" fill=\"freeze\" from=").append(shape.getSpecs().get(0))
                  .append(" to= ").append(shape.getSpecs().get(0) * resize.getFactor())
                  .append("/>\n");
        }
      }

      for (ColorChange colorChange : shape.getColorChanges()) {
        tmp.append("\t\t<animate attributeName=\"fill\" attributeType=\"CSS\"")
                .append(" to=\"").append(colorChange.getColor()).append("\" ")
                .append("begin=\"").append(colorChange.getTime()).append("\"/>\n");
      }

      tmp.append("\t</").append(shape.getType()).append(">\n");
    }
    str = str + tmp + "</svg>";
    System.out.print(str);

    try (PrintWriter out = new PrintWriter(outfile)) {
      out.println(str);
    }
  }
}
