SELECT 'YOUNGEST' AS TYPE, name, MAX(birthday)  AS birthday_date
FROM worker
WHERE birthday = (SELECT MAX(birthday) FROM worker)
GROUP BY name

UNION

SELECT 'OLDEST' AS TYPE, name, MIN(birthday)  AS birthday_date
FROM worker
WHERE birthday = (SELECT MIN(birthday) FROM worker)
GROUP BY name

ORDER BY birthday_date DESC;




