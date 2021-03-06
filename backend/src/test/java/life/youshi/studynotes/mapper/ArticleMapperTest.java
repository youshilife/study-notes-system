package life.youshi.studynotes.mapper;

import life.youshi.studynotes.entity.Article;
import org.junit.Assert;
import org.junit.Test;

public class ArticleMapperTest {
    private static final ArticleMapper articleMapper = new ArticleMapper();
    private final Article testArticle = new Article(
        null,
        "/计算机/编程语言/Java",
        0,
        "Java",
        "Java文章内容"
    );

    /**
     * 准备环境：保存测试文章到数据库
     *
     * @return      是否成功
     */
    private boolean insertTestArticle() {
        return articleMapper.insertArticle(testArticle);
    }

    /**
     * 准备环境：从数据库中移除测试文章
     *
     * @return      是否成功
     */
    private boolean deleteTestArticle() {
        return articleMapper.deleteArticle(testArticle);
    }

    @Test
    public void selectArticleById() {
        try {
            insertTestArticle();

            Article article = articleMapper.selectArticleById(testArticle.getId());

            System.out.println(article);
            Assert.assertNotNull(article);
            Assert.assertEquals(testArticle.getId().intValue(), article.getId().intValue());
        } finally {
            deleteTestArticle();
        }
    }

    @Test
    public void selectArticleByPath() {
        try {
            insertTestArticle();

            Article article = articleMapper.selectArticleByPath(testArticle.getPath());

            System.out.println(article);
            Assert.assertNotNull(article);
            Assert.assertEquals(testArticle.getId().intValue(), article.getId().intValue());
        } finally {
            deleteTestArticle();
        }
    }

    @Test
    public void insertArticle() {
        try {
            boolean success = insertTestArticle();

            Assert.assertTrue(success);
            Assert.assertNotNull(testArticle.getId());
        } finally {
            deleteTestArticle();
        }
    }

    @Test
    public void updateArticle() {
        try {
            insertTestArticle();

            testArticle.setPath("/数学/线性代数");
            testArticle.setTitle("线性代数");

            boolean success = articleMapper.updateArticle(testArticle);
            Article article = articleMapper.selectArticleById(testArticle.getId());

            Assert.assertTrue(success);
            Assert.assertEquals("/数学/线性代数", article.getPath());
            Assert.assertEquals("线性代数", article.getTitle());
        } finally {
            deleteTestArticle();
        }
    }

    @Test
    public void deleteArticleById() {
        insertTestArticle();

        boolean success = articleMapper.deleteArticleById(testArticle.getId());
        Article article = articleMapper.selectArticleById(testArticle.getId());

        Assert.assertTrue(success);
        Assert.assertNull(article);
    }

    @Test
    public void deleteArticle() {
        insertTestArticle();

        boolean success = deleteTestArticle();
        Article article = articleMapper.selectArticleById(testArticle.getId());

        Assert.assertTrue(success);
        Assert.assertNull(article);
    }
}
