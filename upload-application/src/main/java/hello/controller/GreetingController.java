package hello.controller;

import java.util.concurrent.atomic.AtomicLong;
import hello.model.GreetingModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("spring-rest")
public class GreetingController {
  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/greeting")
  public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    model.addAttribute("name", name);
    return "greeting";
  }

  @GetMapping("/hello")
  public GreetingModel greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new GreetingModel(counter.incrementAndGet(),
        String.format(template, name));
  }

  @GetMapping(
      value = "/ex/foos",
      headers = { "key1=val1", "key2=val2" }
  )
  public String getFoosWithHeaders() {
    return "Get some Foos with Header";
  }

  @GetMapping(
      value = "/ex/foos",
      produces = { "application/json", "application/xml" }
  )
  public String getFoosAsJsonFromBrowser() {
    return "Get some Foos with Header Old";
  }

  @GetMapping(value = "/ex/foos/{fooid}/bar/{barid}")
  public String getFoosBySimplePathWithPathVariables
      (@PathVariable long fooid, @PathVariable long barid) {
    return "Get a specific Bar with id=" + barid +
        " from a Foo with id=" + fooid;
  }

  @GetMapping(value = "/ex/bars/{numericId:[\\d]+}")
  public String getBarsBySimplePathWithPathVariable(
      @PathVariable long numericId) {
    return "Get a specific Bar with id=" + numericId;
  }

  @GetMapping(
      value = "/ex/bars",
      params = { "id", "second" })
  public String getBarBySimplePathWithExplicitRequestParams(
      @RequestParam("id") long id, @RequestParam("second") String second) {
    return "Narrow Get a specific Bar with id=" + id;
  }

  @GetMapping(
      value = { "/ex/advanced/bars", "/ex/advanced/foos" })
  public String getFoosOrBarsByPath() {
    return "Advanced - Get some Foos or Bars";
  }

  @RequestMapping(
      value = "/ex/foos/multiple",
      method = { RequestMethod.PUT, RequestMethod.POST }
  )
  public String putAndPostFoos() {
    return "Advanced - PUT and POST within single method";
  }

  @GetMapping(value = "*")
  public String getFallback() {
    return "Fallback for GET Requests";
  }

}
