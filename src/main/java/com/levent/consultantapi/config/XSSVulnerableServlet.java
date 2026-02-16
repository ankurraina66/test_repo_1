import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class XSSVulnerableServlet extends HttpServlet {

    private static List<String> comments = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String comment = request.getParameter("comment");
        comments.add(comment); // ❌ stored without sanitization

        String x = request.getParameter("x");
        String y = x;
        response.getWriter().println("<p>" + y + "</p>");

        response.sendRedirect("/comment");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        for (String c : comments) {
            // ❌ Stored XSS
            out.println("<p>" + c + "</p>");
        }
    }
}
