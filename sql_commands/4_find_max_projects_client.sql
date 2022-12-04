SELECT client.name, count(project.id) AS project_count
FROM client
JOIN project ON client.id = project.client_id
GROUP BY name
HAVING COUNT(project.id) IN (
       SELECT COUNT(project.id)
       FROM project
       GROUP BY client_id
       ORDER BY count(project.id) DESC
       LIMIT 1
);