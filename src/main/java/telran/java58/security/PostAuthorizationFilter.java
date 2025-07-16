package telran.java58.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import telran.java58.accounting.dao.UserAccountRepository;
import telran.java58.accounting.model.Role;
import telran.java58.accounting.model.UserAccount;
import telran.java58.post.dao.PostRepository;
import telran.java58.post.dto.exception.PostNotFoundException;
import telran.java58.post.model.Post;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;

@Component
@Order(15)
@RequiredArgsConstructor
public class PostAuthorizationFilter implements Filter {

    private final PostRepository postRepository;
    private final UserAccountRepository userRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        Principal principal = httpReq.getUserPrincipal();

        if (principal == null) {
            httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String login = principal.getName();

        if (checkEndPoint(httpReq.getMethod(),httpReq.getServletPath())) {
            String postId = extractPostId(httpReq.getServletPath());

            if (postId == null || postId.isEmpty()) {
                httpRes.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

            UserAccount user = userRepository.findById(login).orElseThrow();

            boolean isAdmin = user.getRoles().contains(Role.ADMINISTRATOR);
            boolean isAuthor = post.getAuthor().equalsIgnoreCase(login);

            if (!isAdmin && !isAuthor) {
                httpRes.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean checkEndPoint(String method, String path) {
        return (HttpMethod.PATCH.matches(method) && path.matches("/forum/post/\\w+")) ||
                (HttpMethod.DELETE.matches(method) && path.matches("/forum/post/\\w+"));
    }

    private String extractPostId(String path) {
        String[] parts = path.split("/");
        int index = Arrays.asList(parts).indexOf("post");
        return (index >= 0 && index + 1 < parts.length) ? parts[index + 1] : null;
    }
}
